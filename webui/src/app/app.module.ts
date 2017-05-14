import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { FooterComponent } from './shared/footer/footer.component';
import { HomeComponent } from './home/home.component';

import { AboutComponent } from './about/about.component';
import { TeamComponent } from './team/team.component';
import { NotFoundComponent } from './notfound.component';

import { routing } from './app.routing';

import { RecordModalComponent } from './recordmodal/recordModal.component';
import {ModalModule} from "angular2-modal";
import {BootstrapModalModule} from "angular2-modal/plugins/bootstrap";


@NgModule({
    declarations: [
        AppComponent,
        NavbarComponent,
        FooterComponent,
        HomeComponent,
        AboutComponent,
        TeamComponent,
        NotFoundComponent,
        RecordModalComponent
    ],
    entryComponents: [
        RecordModalComponent],
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        ModalModule.forRoot(),
        BootstrapModalModule,
        NgbModule.forRoot(),
        routing
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule { }
