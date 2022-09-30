import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { TodosComponent } from './todos/todos.component';
import { SingleTodoComponent } from './single-todo/single-todo.component';
import { TodoInputComponent } from './todo-input/todo-input.component';

@NgModule({
  declarations: [
    AppComponent,
    TodosComponent,
    SingleTodoComponent,
    TodoInputComponent,
  ],
  imports: [BrowserModule, FormsModule, HttpClientModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
