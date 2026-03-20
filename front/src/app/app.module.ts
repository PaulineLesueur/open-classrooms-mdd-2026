import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { InputTextModule } from 'primeng/inputtext';
import { NavbarComponent } from './shared/components/navbar/navbar.component';
import { MenubarModule } from 'primeng/menubar';
import { RippleModule } from 'primeng/ripple';
import { AvatarModule } from 'primeng/avatar';
import { RegisterComponent } from './features/auth/register/register.component';
import { LoginComponent } from './features/auth/login/login.component';
import { FeedComponent } from './features/posts/feed/feed.component';
import { TopicsListComponent } from './features/topics/topics-list/topics-list.component';
import { PostDetailComponent } from './features/posts/post-detail/post-detail.component';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { CreateFormComponent } from './features/posts/create-form/create-form.component';
import { DropdownModule } from 'primeng/dropdown';
import { ProfileComponent } from './features/profile/profile.component';
import { PasswordModule } from 'primeng/password';
import { SidebarModule } from 'primeng/sidebar';
import { MessageService } from 'primeng/api';
import { ToastModule } from 'primeng/toast';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from './core/interceptors/auth.interceptor';

@NgModule({
  declarations: [AppComponent, HomeComponent, NavbarComponent, RegisterComponent, LoginComponent, FeedComponent, TopicsListComponent, PostDetailComponent, CreateFormComponent, ProfileComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ButtonModule,
    CardModule,
    MenubarModule,
    InputTextModule,
    RippleModule,
    AvatarModule,
    InputTextareaModule,
    DropdownModule,
    PasswordModule,
    SidebarModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    ToastModule,
  ],
  providers: [
    MessageService,
    {
        provide: HTTP_INTERCEPTORS,
        useClass: AuthInterceptor,
        multi: true
    }
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
