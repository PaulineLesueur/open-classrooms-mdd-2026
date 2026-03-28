import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { RegisterComponent } from './features/auth/register/register.component';
import { LoginComponent } from './features/auth/login/login.component';
import { FeedComponent } from './features/posts/feed/feed.component';
import { TopicsListComponent } from './features/topics/topics-list/topics-list.component';
import { PostDetailComponent } from './features/posts/post-detail/post-detail.component';
import { CreateFormComponent } from './features/posts/create-form/create-form.component';
import { ProfileComponent } from './features/profile/profile.component';
import { AuthGuard } from './core/guards/auth.guard';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'auth/register', component: RegisterComponent },
  { path: 'auth/login', component: LoginComponent },
  { path: 'feed', component: FeedComponent, canActivate: [AuthGuard] },
  { path: 'topics', component: TopicsListComponent, canActivate: [AuthGuard] },
  { path: 'post/detail/:id', component: PostDetailComponent, canActivate: [AuthGuard] },
  { path: 'post/create', component: CreateFormComponent, canActivate: [AuthGuard] },
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
