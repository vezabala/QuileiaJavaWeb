import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data } from '@angular/router';

import { QuileiaJavaWebTestModule } from '../../../test.module';
import { FranjaHorariaComponent } from 'app/entities/franja-horaria/franja-horaria.component';
import { FranjaHorariaService } from 'app/entities/franja-horaria/franja-horaria.service';
import { FranjaHoraria } from 'app/shared/model/franja-horaria.model';

describe('Component Tests', () => {
  describe('FranjaHoraria Management Component', () => {
    let comp: FranjaHorariaComponent;
    let fixture: ComponentFixture<FranjaHorariaComponent>;
    let service: FranjaHorariaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [QuileiaJavaWebTestModule],
        declarations: [FranjaHorariaComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: {
                subscribe: (fn: (value: Data) => void) =>
                  fn({
                    pagingParams: {
                      predicate: 'id',
                      reverse: false,
                      page: 0
                    }
                  })
              }
            }
          }
        ]
      })
        .overrideTemplate(FranjaHorariaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FranjaHorariaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FranjaHorariaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new FranjaHoraria(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.franjaHorarias && comp.franjaHorarias[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new FranjaHoraria(123)],
            headers
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.franjaHorarias && comp.franjaHorarias[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
