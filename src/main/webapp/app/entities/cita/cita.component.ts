import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICita } from 'app/shared/model/cita.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { CitaService } from './cita.service';
import { CitaDeleteDialogComponent } from './cita-delete-dialog.component';
import { BusquedaCita } from 'app/entities/model/busquedaCita';

@Component({
  selector: 'jhi-cita',
  templateUrl: './cita.component.html'
})
export class CitaComponent implements OnInit, OnDestroy {
  citas?: ICita[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  citasList: any[] = [];
  medicos: any[] = [];
  pacientes: any[] = [];
  medicoElegido: any = null;
  pacienteElegido: any = null;

  busqueda: BusquedaCita = {
    medico: '',
    paciente: ''
  };

  constructor(
    protected citaService: CitaService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.citaService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<ICita[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
      this.listaCitas();
      this.listaMedicosC();
      this.listaPacientesC();
    });
    this.registerChangeInCitas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICita): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCitas(): void {
    this.eventSubscriber = this.eventManager.subscribe('citaListModification', () => this.loadPage());
  }

  delete(cita: ICita): void {
    const modalRef = this.modalService.open(CitaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.cita = cita;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: ICita[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/cita'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.citas = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
  listaMedicosC(): void {
    this.citaService.medicos().subscribe(
      data => {
        this.medicos = data;
      },
      () => this.onError()
    );
  }
  listaPacientesC(): void {
    this.citaService.pacientes().subscribe(
      data => {
        this.pacientes = data;
      },
      () => this.onError()
    );
  }
  listaCitas(): void {
    this.citaService.citas(this.busqueda).subscribe(
      data => {
        this.citasList = data;
      },
      () => this.onError()
    );
  }

  clear(): void {
    this.busqueda.medico = '';
    this.busqueda.paciente = '';
    this.listaCitas();
  }
  onChangeCita(): void {
    if (this.medicoElegido) {
      this.busqueda.medico = this.medicoElegido.nombreCompleto;
    } else {
      this.busqueda.medico = '';
    }
    if (this.pacienteElegido) {
      this.busqueda.paciente = this.pacienteElegido.nombreCompleto;
    } else {
      this.busqueda.paciente = '';
    }
    this.listaCitas();
  }
}
