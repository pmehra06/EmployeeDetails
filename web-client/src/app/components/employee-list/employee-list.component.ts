import {Component, Directive, EventEmitter, Input, OnInit, Output, QueryList, ViewChildren} from '@angular/core';
import {Employee} from '../../interfaces/employee';
import {EmployeeService} from '../../services/employee.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {EmployeeModalComponent} from '../employee-modal/employee-modal.component';


export type SortColumn = keyof Employee | '';
export type SortDirection = 'asc' | 'desc' | '';
const rotate: {[key: string]: SortDirection} = { 'asc': 'desc', 'desc': '', '': 'asc' };

const compare = (v1: string | number | Date, v2: string | number | Date) => v1 < v2 ? -1 : v1 > v2 ? 1 : 0;

export interface SortEvent {
  column: SortColumn;
  direction: SortDirection;
}

@Directive({
  selector: 'th[sortable]',
  host: {
    '[class.asc]': 'direction === "asc"',
    '[class.desc]': 'direction === "desc"',
    '(click)': 'rotate()'
  }
})
export class NgbdSortableHeader {

  @Input() sortable: SortColumn = '';
  @Input() direction: SortDirection = '';
  @Output() sort = new EventEmitter<SortEvent>();

  rotate() {
    this.direction = rotate[this.direction];
    this.sort.emit({column: this.sortable, direction: this.direction});
  }
}

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.scss']
})
export class EmployeeListComponent implements OnInit {
  private employees: Employee[] = [];

  constructor(private employeeService: EmployeeService, private modalService: NgbModal) {
    this.employeeService.getEmployees().subscribe(
      emp => this.employees = emp
    );
  }

  @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;

  ngOnInit() {
  }

  onSort({column, direction}: SortEvent) {

    // resetting other headers
    this.headers.forEach(header => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    // sorting employees

      this.employees = this.employees.sort((a, b) => {
        const res = compare(a[column], b[column]);
        return direction === 'asc' ? res : -res;
      });
  }

  openEditModal(emp: Employee) {
    const modalRef = this.modalService.open(EmployeeModalComponent);
    modalRef.componentInstance.employee = emp;
    modalRef.componentInstance.kind = 'Edit';
    const date = new Date(emp.dob);
    modalRef.componentInstance.empDob = {
      'month': date.getMonth() + 1,
      'year': date.getFullYear(),
      'day': date.getDate()
    };

    modalRef.result.then((result) => {
      console.log(result);
    }, (reason) => {
    });
  }

  delete(id: string) {
    this.employeeService.deleteEmployee(id).subscribe(
      res => this.refreshPage()
    );
  }

  refreshPage() {
    this.employeeService.getEmployees().subscribe(
      emps => this.employees = emps
    );
  }
}





