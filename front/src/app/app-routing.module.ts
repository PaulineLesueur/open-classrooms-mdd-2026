import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { RegisterComponent } from './features/auth/register/register.component';
import { LoginComponent } from './features/auth/login/login.component';
import { FeedComponent } from './features/articles/feed/feed.component';
import { ThemesListComponent } from './features/themes/themes-list/themes-list.component';
import { ArticleDetailComponent } from './features/articles/article-detail/article-detail.component';
import { CreateFormComponent } from './features/articles/create-form/create-form.component';
import { ProfileComponent } from './features/profile/profile.component';

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'auth/register', component: RegisterComponent },
  { path: 'auth/login', component: LoginComponent },
  { path: 'feed', component: FeedComponent },
  { path: 'themes', component: ThemesListComponent },
  { path: 'article/detail', component: ArticleDetailComponent },
  { path: 'article/create', component: CreateFormComponent },
  { path: 'profile', component: ProfileComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
