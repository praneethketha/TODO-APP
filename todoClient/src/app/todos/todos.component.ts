import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  Output,
  SimpleChanges,
} from '@angular/core';
import { TodosService } from '../service/todos.service';
import ITodo from '../Todo';

@Component({
  selector: 'app-todos',
  templateUrl: './todos.component.html',
  styleUrls: ['./todos.component.css'],
})
export class TodosComponent implements OnChanges {
  @Input() searchTerm: string = '';
  @Input() todos: ITodo[] = [];
  @Output() populate = new EventEmitter<ITodo>();
  @Output() complete = new EventEmitter<ITodo>();
  @Output() delete = new EventEmitter<number>();
  @Output() get = new EventEmitter<string>();

  constructor(public todoService: TodosService) {}

  ngOnInit() {
    console.log(
      this.todoService.prinicipal.subscribe((value) => console.log(value))
    );
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['searchTerm']) {
      this.get.emit(changes['searchTerm'].currentValue);
    }
  }
  public fireDelete(id: number | undefined) {
    this.delete.emit(id);
  }

  public firePopulate(todo: ITodo) {
    this.populate.emit(todo);
  }

  public fireComplete(todo: ITodo) {
    this.complete.emit(todo);
  }
}
