import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { FranjaHorariaService } from 'app/entities/franja-horaria/franja-horaria.service';
import { IFranjaHoraria, FranjaHoraria } from 'app/shared/model/franja-horaria.model';
import { Estado } from 'app/shared/model/enumerations/estado.model';

describe('Service Tests', () => {
  describe('FranjaHoraria Service', () => {
    let injector: TestBed;
    let service: FranjaHorariaService;
    let httpMock: HttpTestingController;
    let elemDefault: IFranjaHoraria;
    let expectedResult: IFranjaHoraria | IFranjaHoraria[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(FranjaHorariaService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new FranjaHoraria(0, 'AAAAAAA', Estado.ACTIVO);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a FranjaHoraria', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new FranjaHoraria()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a FranjaHoraria', () => {
        const returnedFromService = Object.assign(
          {
            franja: 'BBBBBB',
            estadoFranjaHoraria: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of FranjaHoraria', () => {
        const returnedFromService = Object.assign(
          {
            franja: 'BBBBBB',
            estadoFranjaHoraria: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a FranjaHoraria', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
