import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginComponent } from './components/login/login.component';
import { CadastrarComponent } from './components/cadastrar/cadastrar.component';
import { VerRefeicoesComponent } from './components/ver-refeicoes/ver-refeicoes.component';
import { funcionarioGuard } from './guards/funcionario.guard';
import { MenuComponent } from './components/menu/menu.component';
import { VerAvisoComponent } from './components/ver-aviso/ver-aviso.component';
import { EnviarFeedbackComponent } from './components/enviar-feedback/enviar-feedback.component';
import { EnviarAvisosComponent } from './components/enviar-aviso/enviar-aviso.component';
import { ComprarRefeicaoComponent } from './components/comprar-refeicao/comprar-refeicao.component';
import { MenuRefeicoesComponent } from './components/menu-refeicoes/menu-refeicoes.component';
import { MinhasRefeicoesComponent } from './components/minhas-refeicoes/minhas-refeicoes.component';
import { AdicionarSaldoComponent } from './components/adicionar-saldo/adicionar-saldo.component';
import { MenuFuncionarioComponent } from './components/menu-funcionario/menu-funcionario.component';
import { ValidarRefeicaoComponent } from './components/validar-refeicao/validar-refeicao.component';
import { menuGuard } from './guards/menu.guard';
import { clienteGuard } from './guards/cliente.guard';

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
        component: VerRefeicoesComponent,
        canActivate: [funcionarioGuard]


    },
    {
        path: "enviar-feedback",
        component: EnviarFeedbackComponent,
        canActivate: [clienteGuard]

    },
    {
        path: "enviar-feedback/:data/:turno",
        component: EnviarFeedbackComponent,
        canActivate: [clienteGuard]

    },
    {
        path: "comprar-refeicao",
        component: ComprarRefeicaoComponent,
        canActivate: [clienteGuard]

    },
    {
        path: "menu-refeicoes",
        component: MenuRefeicoesComponent,
        canActivate: [clienteGuard]

    },
    {
        path: "minhas-refeicoes",
        component: MinhasRefeicoesComponent,
        canActivate: [clienteGuard]

    },
    {
        path: "adicionar-saldo",
        component: AdicionarSaldoComponent,
        canActivate: [clienteGuard]

    },
    {
        path:"avisos",
        component: VerAvisoComponent,
        children:[{
            path:"",
            component: NavbarComponent
        }],
    },
    {
        path: 'menu',
        component: MenuComponent,
        children:[{
            path:"",
            component: NavbarComponent
        }],
        canActivate: [menuGuard]

    },
    {
        path: 'enviar-aviso',
        component: EnviarAvisosComponent,
        canActivate: [funcionarioGuard]

    },
    {
        path: 'menu-funcionario',
        component: MenuFuncionarioComponent,
        canActivate: [menuGuard]
    },
    {
        path:'validar-refeicao',
        component:ValidarRefeicaoComponent,
        canActivate: [funcionarioGuard]
    },
    {
        path:"validar-refeicao/:token",
        component:ValidarRefeicaoComponent,
        canActivate:[funcionarioGuard]
    },
    {
        path: "**",
        redirectTo:'home'
    }

];
