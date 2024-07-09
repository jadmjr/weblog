import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from "./navbar/navbar.component";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';



@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
    imports: [RouterOutlet, NavbarComponent, BrowserAnimationsModule]
})
export class AppComponent {
  title = 'weblog-app';
}
