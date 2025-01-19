import { Routes } from '@angular/router';
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
import { AdicionarSaldoComponent } from './components/adicionar-saldo/adicionar-saldo.component';

export const routes: Routes = [
    {
        path: 'home',
        component:HomeComponent,
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
        path: "adicionar-saldo",
        component: AdicionarSaldoComponent
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
        path: "**",
        redirectTo:'home'
    }
    
];
