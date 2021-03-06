import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMedico } from 'app/shared/model/medico.model';

import { BusquedaMedico } from 'app/entities/model/busquedaMedico';

type EntityResponseType = HttpResponse<IMedico>;
type EntityArrayResponseType = HttpResponse<IMedico[]>;

@Injectable({ providedIn: 'root' })
export class MedicoService {
  public resourceUrl = SERVER_API_URL + 'apis/medicos';
  public franjaHorariaUrl = SERVER_API_URL + 'api/franja-horarias';

  constructor(protected http: HttpClient) {}

  create(medico: IMedico): Observable<EntityResponseType> {
    return this.http.post<IMedico>(this.resourceUrl, medico, { observe: 'response' });
  }

  update(medico: IMedico): Observable<EntityResponseType> {
    return this.http.put<IMedico>(this.resourceUrl, medico, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMedico>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMedico[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
  franjaHorarias(): Observable<any[]> {
    return this.http.get<any[]>(this.franjaHorariaUrl + '/list');
  }

  medicos(busqueda: BusquedaMedico): Observable<any[]> {
    return this.http.post<any[]>(this.resourceUrl + '/list', busqueda);
  }
}
