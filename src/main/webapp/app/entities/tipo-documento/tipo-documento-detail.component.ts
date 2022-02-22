import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipoDocumento } from 'app/shared/model/tipo-documento.model';

@Component({
  selector: 'jhi-tipo-documento-detail',
  templateUrl: './tipo-documento-detail.component.html'
})
export class TipoDocumentoDetailComponent implements OnInit {
  tipoDocumento: ITipoDocumento | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tipoDocumento }) => (this.tipoDocumento = tipoDocumento));
  }

  previousState(): void {
    window.history.back();
  }
}
