import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {NgbActiveModal, NgbCalendar, NgbDate, NgbDateStruct, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {Employee} from '../../interfaces/employee';
import {EmployeeService} from '../../services/employee.service';

@Component({
  selector: 'app-employee-modal',
  templateUrl: './employee-modal.component.html',
  styleUrls: ['./employee-modal.component.scss']
})
export class EmployeeModalComponent implements OnInit {
  private name: any;
  private employee: Employee;
  private tempEmployee: Employee;
  private empDob: NgbDate;
  private tempDob: NgbDate;
  model: NgbDateStruct;
  placement = 'bottom';
  private kind = 'Update';
  private showSpinner = false;

  constructor(private modalService: NgbModal, private activeModal: NgbActiveModal, private employeeService: EmployeeService,
              private calendar: NgbCalendar) {
    this.calendar.getToday();
  }

  ngOnInit() {
    this.tempEmployee = Object.assign({}, this.employee);
    this.tempDob = this.empDob;
  }

  updateEmployee() {
    this.showSpinner = true;
    this.tempEmployee.dob = new Date(this.tempDob.year, this.tempDob.month, this.tempDob.day);
    if (this.kind === 'Edit') {
      this.employeeService.updateEmployee(this.employee.id, this.tempEmployee).subscribe(res => {
        this.showSpinner = false;
        this.activeModal.close(name);
      });
    } else if (this.kind === 'Add') {
      this.employeeService.createEmployee(this.tempEmployee).subscribe(
        res => {
          this.showSpinner = false;
          this.activeModal.close(name);
        }
      );
    }
  }
}
