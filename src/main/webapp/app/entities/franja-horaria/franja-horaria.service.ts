import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFranjaHoraria } from 'app/shared/model/franja-horaria.model';

type EntityResponseType = HttpResponse<IFranjaHoraria>;
type EntityArrayResponseType = HttpResponse<IFranjaHoraria[]>;

@Injectable({ providedIn: 'root' })
export class FranjaHorariaService {
  public resourceUrl = SERVER_API_URL + 'api/franja-horarias';

  constructor(protected http: HttpClient) {}

  create(franjaHoraria: IFranjaHoraria): Observable<EntityResponseType> {
    return this.http.post<IFranjaHoraria>(this.resourceUrl, franjaHoraria, { observe: 'response' });
  }

  update(franjaHoraria: IFranjaHoraria): Observable<EntityResponseType> {
    return this.http.put<IFranjaHoraria>(this.resourceUrl, franjaHoraria, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFranjaHoraria>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFranjaHoraria[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
