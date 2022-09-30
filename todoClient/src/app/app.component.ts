import { Component, OnInit } from '@angular/core';
import { TodosService } from './service/todos.service';
import ITodo from './Todo';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  public searchTerm: string = '';
  public todos: ITodo[] = [];
  public todo: ITodo = { title: '', completed: false };

  constructor(public todoService: TodosService) {}

  ngOnInit(): void {
    this.getTodos();
  }

  handleSubmit(id?: number) {
    if (id) {
      this.todoService.editTodo(id, this.todo).subscribe(() => {
        this.getTodos();
        this.todo.id = undefined;
      });
    } else {
      this.todoService.addTodo(this.todo).subscribe(() => this.getTodos());
    }
  }

  public populateTodo(todo: ITodo) {
    this.todo = todo;
  }

  public completeTodo(data: ITodo) {
    this.todoService
      .editTodo(data.id, { ...data, completed: !data.completed })
      .subscribe(
        (res) => {
          this.getTodos();
        },
        (err) => alert(err.message)
      );
  }

  public deleteTodo(id: number) {
    this.todoService.removeTodo(id).subscribe(
      () => {
        this.getTodos();
      },
      (err) => alert(err.message)
    );
  }

  public getTodos(searchTerm?: string) {
    const search = searchTerm ? searchTerm : this.searchTerm;
    this.todoService.getTodos(search).subscribe(
      (res) => {
        this.todos = res;
      },
      (err) => alert(err.message)
    );
  }
}
