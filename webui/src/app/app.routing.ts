import { Routes,RouterModule } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { TeamComponent } from './team/team.component';
import { NotFoundComponent } from './notfound.component';


export const routing = RouterModule.forRoot([
  {path: '', component: HomeComponent},
  {path: 'about', component: AboutComponent},
  {path: 'team', component: TeamComponent},
  {path: '**', component: NotFoundComponent},
]);
