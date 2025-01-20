import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuFuncionarioComponent } from './menu-funcionario.component';

describe('MenuFuncionarioComponent', () => {
  let component: MenuFuncionarioComponent;
  let fixture: ComponentFixture<MenuFuncionarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MenuFuncionarioComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MenuFuncionarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
