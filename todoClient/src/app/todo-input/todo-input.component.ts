import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { TodosService } from '../service/todos.service';
import ITodo from '../Todo';

@Component({
  selector: 'app-todo-input',
  templateUrl: './todo-input.component.html',
  styleUrls: ['./todo-input.component.css'],
})
export class TodoInputComponent implements OnInit {
  @Input() todo: ITodo = { title: '', completed: false };
  @Output() submit = new EventEmitter();

  constructor(public todoService: TodosService) {}

  ngOnInit(): void {}

  fireSubmit(id?: number) {
    this.todoService.addPrincipal('New principal');
    this.submit.emit(id);
  }
}
