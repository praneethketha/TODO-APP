import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-single-todo',
  templateUrl: './single-todo.component.html',
  styleUrls: ['./single-todo.component.css'],
})
export class SingleTodoComponent implements OnInit {
  @Input() todo = { title: '', completed: false };

  constructor() {}

  ngOnInit(): void {}
}
