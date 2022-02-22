import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFranjaHoraria } from 'app/shared/model/franja-horaria.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { FranjaHorariaService } from './franja-horaria.service';
import { FranjaHorariaDeleteDialogComponent } from './franja-horaria-delete-dialog.component';

@Component({
  selector: 'jhi-franja-horaria',
  templateUrl: './franja-horaria.component.html'
})
export class FranjaHorariaComponent implements OnInit, OnDestroy {
  franjaHorarias?: IFranjaHoraria[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected franjaHorariaService: FranjaHorariaService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.franjaHorariaService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IFranjaHoraria[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInFranjaHorarias();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IFranjaHoraria): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInFranjaHorarias(): void {
    this.eventSubscriber = this.eventManager.subscribe('franjaHorariaListModification', () => this.loadPage());
  }

  delete(franjaHoraria: IFranjaHoraria): void {
    const modalRef = this.modalService.open(FranjaHorariaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.franjaHoraria = franjaHoraria;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IFranjaHoraria[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/franja-horaria'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.franjaHorarias = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
