import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEspecialidad } from 'app/shared/model/especialidad.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { EspecialidadService } from './especialidad.service';
import { EspecialidadDeleteDialogComponent } from './especialidad-delete-dialog.component';

@Component({
  selector: 'jhi-especialidad',
  templateUrl: './especialidad.component.html'
})
export class EspecialidadComponent implements OnInit, OnDestroy {
  especialidads?: IEspecialidad[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected especialidadService: EspecialidadService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.especialidadService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IEspecialidad[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    });
    this.registerChangeInEspecialidads();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEspecialidad): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEspecialidads(): void {
    this.eventSubscriber = this.eventManager.subscribe('especialidadListModification', () => this.loadPage());
  }

  delete(especialidad: IEspecialidad): void {
    const modalRef = this.modalService.open(EspecialidadDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.especialidad = especialidad;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IEspecialidad[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/especialidad'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.especialidads = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
