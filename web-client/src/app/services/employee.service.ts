import { Injectable } from '@angular/core';
import {Observable, throwError} from 'rxjs';
import {Employee} from '../interfaces/employee';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private serverUrl = 'http://localhost:2222';
  private url = `./rest/employees`;


  constructor(private http: HttpClient) { }

  getEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.url);
  }

  createEmployee(data: Employee): Observable<Employee> {
    return this.http.post<Employee>(this.url, data).pipe(catchError(this.error));
  }

  updateEmployee(id: string, data: Employee): Observable<Employee> {
    return this.http.put<Employee>(this.url + `/${id}`, data).pipe(catchError(this.error));
  }

  deleteEmployee(id: string): Observable<Employee> {
    return this.http.delete<Employee>(this.url + `/${id}`).pipe(catchError(this.error));
  }

  error(error: HttpErrorResponse) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  }
}
