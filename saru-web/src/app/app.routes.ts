import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component'; 
import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginComponent } from './components/login/login.component';
import { CadastrarComponent } from './components/cadastrar/cadastrar.component';
import { VerAvisoComponent } from './components/ver-aviso/ver-aviso.component';
import { MenuComponent } from './components/menu/menu.component';
import { authGuard } from './guards/auth.guard';

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
        path: "**",
        redirectTo:'home'
    }
    

];
