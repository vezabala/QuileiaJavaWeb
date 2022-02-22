import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { QuileiaJavaWebTestModule } from '../../../test.module';
import { FranjaHorariaDetailComponent } from 'app/entities/franja-horaria/franja-horaria-detail.component';
import { FranjaHoraria } from 'app/shared/model/franja-horaria.model';

describe('Component Tests', () => {
  describe('FranjaHoraria Management Detail Component', () => {
    let comp: FranjaHorariaDetailComponent;
    let fixture: ComponentFixture<FranjaHorariaDetailComponent>;
    const route = ({ data: of({ franjaHoraria: new FranjaHoraria(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [QuileiaJavaWebTestModule],
        declarations: [FranjaHorariaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(FranjaHorariaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FranjaHorariaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load franjaHoraria on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.franjaHoraria).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
