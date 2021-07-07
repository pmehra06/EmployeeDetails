import {Component, OnInit} from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {EmployeeModalComponent} from '../employee-modal/employee-modal.component';
import {Employee} from '../../interfaces/employee';
import {Gender} from '../../enums/gender.enum';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  ngOnInit() {
  }

  constructor(private modalService: NgbModal) {
  }

  openEditDialog() {
    const modalRef = this.modalService.open(EmployeeModalComponent);
    const tempEmployee: Employee = {
      'id': '',
      'firstName': '',
      'lastName': '',
      'department': '',
      'dob': null,
      'gender': 'MALE'
    };
    modalRef.componentInstance.employee = tempEmployee;
    modalRef.componentInstance.kind = 'Add';
    modalRef.result.then((result) => {
      console.log(result);
    }, (reason) => {
    });
  }
}
