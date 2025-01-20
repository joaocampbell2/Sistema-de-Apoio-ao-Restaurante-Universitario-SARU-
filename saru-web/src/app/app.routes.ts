import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component'; 
import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginComponent } from './components/login/login.component';
import { CadastrarComponent } from './components/cadastrar/cadastrar.component';
import { VerRefeicoesComponent } from './components/ver-refeicoes/ver-refeicoes.component';
import { authGuard } from './guards/auth.guard';
import { MenuComponent } from './components/menu/menu.component';
import { VerAvisoComponent } from './components/ver-aviso/ver-aviso.component';
import { EnviarFeedbackComponent } from './components/enviar-feedback/enviar-feedback.component';
import { EnviarAvisosComponent } from './components/enviar-aviso/enviar-aviso.component';
import { ComprarRefeicaoComponent } from './components/comprar-refeicao/comprar-refeicao.component';
import { MenuRefeicoesComponent } from './components/menu-refeicoes/menu-refeicoes.component';
import { MinhasRefeicoesComponent } from './components/minhas-refeicoes/minhas-refeicoes.component';
import { MenuFuncionarioComponent } from './components/menu-funcionario/menu-funcionario.component';

export const routes: Routes = [
    {
        path: 'home',
        component:HomeComponent,
        children:[{
            path:"",
            component: NavbarComponent
        }]
    },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path:"cadastrar",
        component: CadastrarComponent
    },
    {
        path: 'ver-refeicoes',
        component: VerRefeicoesComponent
    },
    {
        path: "enviar-feedback",
        component: EnviarFeedbackComponent
    },
    {
        path: "enviar-feedback/:data/:turno",
        component: EnviarFeedbackComponent
    },
    {
        path: "comprar-refeicao",
        component: ComprarRefeicaoComponent
    },
    {
        path: "menu-refeicoes",
        component: MenuRefeicoesComponent
    },
    {
        path: "minhas-refeicoes",
        component: MinhasRefeicoesComponent
    },
    {
        path:"avisos",
        component: VerAvisoComponent,
        children:[{
            path:"",
            component: NavbarComponent
        }],
        canActivate:[authGuard]
    },
    {
        path: 'menu',
        component: MenuComponent,
        children:[{
            path:"",
            component: NavbarComponent
        }],
        canActivate: [authGuard]
        
    },
    {
        path: 'enviar-aviso',
        component: EnviarAvisosComponent,
    },
    {
        path: 'menu-funcionario',
        component: MenuFuncionarioComponent
    },
    {
        path: 'menu-funcionario',
        component: MenuFuncionarioComponent
    },
    {
        path: "**",
        redirectTo:'home'
    }

];
