import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { QuileiaJavaWebTestModule } from '../../../test.module';
import { EspecialidadDetailComponent } from 'app/entities/especialidad/especialidad-detail.component';
import { Especialidad } from 'app/shared/model/especialidad.model';

describe('Component Tests', () => {
  describe('Especialidad Management Detail Component', () => {
    let comp: EspecialidadDetailComponent;
    let fixture: ComponentFixture<EspecialidadDetailComponent>;
    const route = ({ data: of({ especialidad: new Especialidad(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [QuileiaJavaWebTestModule],
        declarations: [EspecialidadDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EspecialidadDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EspecialidadDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load especialidad on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.especialidad).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
