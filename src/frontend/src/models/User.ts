export class User {
  email: string;
  password: string;
  userType: UserType = UserType.UNKNOWN;
  schoolname: string;
  firstname: string;
  lastname: string;
  sports: Sport[];
  profilePicturePath = "";

  constructor(
    email: string,
    password: string,
    userType: UserType,
    schoolname: string,
    firstname: string,
    lastname: string,
    sports: Sport[]
  ) {
    this.email = email;
    this.password = password;
    this.userType = userType;
    this.schoolname = schoolname;
    this.firstname = firstname;
    this.lastname = lastname;
    this.sports = sports;
  }
}

export enum UserType {
  UNKNOWN = "UNKNOWN",
  TEACHER = "TEACHER",
  SCHOOL = "SCHOOl",
}

export enum Sport {
  UNKNOWN = "UNKNOWN",
  KITESURFING = "KITESURFING",
  KITEFOILING = "KITEFOILING",
  WINGFOILING = "WINGFOILING",
  WINDSURFING = "WINDSURFING",
  WAVERIDING = "WAVERIDING",
  STANDUPPADDLING = "STANDUPPADDLING",
}
