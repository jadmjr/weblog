import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from "./navbar/navbar.component";
import { LogincardComponent } from "./logincard/logincard.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [NavbarComponent, LogincardComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'weblog';
}


