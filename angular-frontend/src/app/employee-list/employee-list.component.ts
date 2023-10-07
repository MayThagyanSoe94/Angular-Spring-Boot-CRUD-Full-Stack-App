import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {
  employees!: Employee[];

  constructor(private employeeService: EmployeeService,
    private router: Router) {

  }

  ngOnInit(): void {
    this.getEmployeeList();
  }
  private getEmployeeList() {
    this.employeeService.getEmployeeList().subscribe(data => {
      this.employees = data;
    });
  }

  getDetails(id: number) {
    this.router.navigate(['update-employee', id]);
  }

  deleteEmployee(id: number) {
    this.employeeService.deleteEmployee(id).subscribe(data => {
      console.log(data);
      this.getEmployeeList();
    })
  }

}
