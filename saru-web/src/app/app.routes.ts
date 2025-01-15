import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component'; 
import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginComponent } from './components/login/login.component';
import { CadastrarComponent } from './components/cadastrar/cadastrar.component';
import { MenuComponent } from './components/menu/menu.component';
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
        path: 'menu',
        component: MenuComponent,
        children:[{
            path:"",
            component: NavbarComponent
        }]
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
