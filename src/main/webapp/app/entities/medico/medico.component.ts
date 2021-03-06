import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMedico } from 'app/shared/model/medico.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { MedicoService } from './medico.service';
import { MedicoDeleteDialogComponent } from './medico-delete-dialog.component';

import { BusquedaMedico } from 'app/entities/model/busquedaMedico';

@Component({
  selector: 'jhi-medico',
  templateUrl: './medico.component.html'
})
export class MedicoComponent implements OnInit, OnDestroy {
  medicos?: IMedico[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  franjaHorarias: any[] = [];
  medicosList: any[] = [];

  franjaHorariaElegido: any = null;

  busqueda: BusquedaMedico = {
    identificacion: '',
    nombreCompleto: '',
    franjaHoraria: ''
  };

  constructor(
    protected medicoService: MedicoService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.medicoService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IMedico[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
      this.listaMedicos();
      this.listaFranjaHorariasM();
    });
    this.registerChangeInMedicos();
  }
  listaFranjaHorariasM(): void {
    this.medicoService.franjaHorarias().subscribe(
      data => {
        this.franjaHorarias = data;
      },
      () => this.onError()
    );
  }
  listaMedicos(): void {
    this.medicoService.medicos(this.busqueda).subscribe(
      data => {
        this.medicosList = data;
      },
      () => this.onError()
    );
  }
  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMedico): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMedicos(): void {
    this.eventSubscriber = this.eventManager.subscribe('medicoListModification', () => this.loadPage());
  }

  delete(medico: IMedico): void {
    const modalRef = this.modalService.open(MedicoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.medico = medico;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IMedico[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/medico'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.medicos = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }

  clearIdentificacion(): void {
    this.busqueda.identificacion = '';
    this.listaMedicos();
  }
  clearNombreCompleto(): void {
    this.busqueda.nombreCompleto = '';
    this.listaMedicos();
  }

  clear(): void {
    this.busqueda.identificacion = '';
    this.busqueda.nombreCompleto = '';
    this.busqueda.franjaHoraria = '';
    this.listaMedicos();
  }
  onChangeMedico1(): void {
    if (this.franjaHorariaElegido) {
      this.busqueda.franjaHoraria = this.franjaHorariaElegido.franja;
    } else {
      this.busqueda.franjaHoraria = '';
    }
    this.listaMedicos();
  }
}
