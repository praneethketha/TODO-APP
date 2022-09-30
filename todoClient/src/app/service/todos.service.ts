import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import ITodo from '../Todo';

@Injectable({
  providedIn: 'root',
})
export class TodosService {
  public prinicipal = new BehaviorSubject<String>("");

  public todos: ITodo[] = [];
  public todo: ITodo = { title: '', completed: false };
  private URL = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  public getTodos(searchTerm?: string): Observable<ITodo[]> {
    return this.http.get<ITodo[]>(`${this.URL}?completed=${searchTerm}`);
  }

  public addTodo(todo: ITodo): Observable<ITodo> {
    return this.http.post<ITodo>(this.URL, todo);
  }

  public editTodo(id: number | undefined, todo: ITodo): Observable<ITodo> {
    return this.http.patch<ITodo>(`${this.URL}/${id}`, todo);
  }

  public removeTodo(id: number | undefined): Observable<void> {
    return this.http.delete<void>(`${this.URL}/${id}`);
  }

  public addPrincipal(prinicipal : String){
    this.prinicipal.next(prinicipal);
  }
}
