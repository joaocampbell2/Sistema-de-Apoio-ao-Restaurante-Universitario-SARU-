import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterOutlet } from '@angular/router';
import { MenuComponent } from "./components/menu/menu.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ReactiveFormsModule, MenuComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'saru-web';
}