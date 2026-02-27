import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
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
import { FeedComponent } from './features/articles/feed/feed.component';
import { ThemesListComponent } from './features/themes/themes-list/themes-list.component';
import { ArticleDetailComponent } from './features/articles/article-detail/article-detail.component';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { CreateFormComponent } from './features/articles/create-form/create-form.component';
import { DropdownModule } from 'primeng/dropdown';
import { ProfileComponent } from './features/profile/profile/profile.component';
import { PasswordModule } from 'primeng/password';

@NgModule({
  declarations: [AppComponent, HomeComponent, NavbarComponent, RegisterComponent, LoginComponent, FeedComponent, ThemesListComponent, ArticleDetailComponent, CreateFormComponent, ProfileComponent],
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
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
