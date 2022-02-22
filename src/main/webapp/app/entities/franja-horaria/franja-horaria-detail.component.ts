import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFranjaHoraria } from 'app/shared/model/franja-horaria.model';

@Component({
  selector: 'jhi-franja-horaria-detail',
  templateUrl: './franja-horaria-detail.component.html'
})
export class FranjaHorariaDetailComponent implements OnInit {
  franjaHoraria: IFranjaHoraria | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ franjaHoraria }) => (this.franjaHoraria = franjaHoraria));
  }

  previousState(): void {
    window.history.back();
  }
}
