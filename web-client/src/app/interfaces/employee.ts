import {Gender} from '../enums/gender.enum';

export interface Employee {
  id: string;
  firstName: string;
  lastName: string;
  department: string;
  dob: Date;
  gender: 'FEMALE' | 'MALE' | 'OTHER';
}
