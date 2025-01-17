import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component'; 
import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginComponent } from './components/login/login.component';
import { CadastrarComponent } from './components/cadastrar/cadastrar.component';

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
        path: "**",
        redirectTo:'home'
    }
];
