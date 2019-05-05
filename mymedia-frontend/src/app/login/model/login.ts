export interface LoginViewModel {
  username: string;
  password: string;
}

export interface RegistrationViewModel extends  LoginViewModel {
  fullname: string;
  email: string;
  password_re: string;
  isAcceptAgreement: boolean;
}

export interface LoginResponse {
  errorCode: number;
  errorMessage: string;
  token: string;
}

export interface RegResponse {
  errorCode: string;
  errorMessage: string;
  userId: string;
}
