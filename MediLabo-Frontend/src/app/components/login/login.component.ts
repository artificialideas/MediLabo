import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
 
@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
    public loginForm: FormGroup = new FormGroup({});
    public loginError: boolean = false;
    public loginErrorMessage: string = "";
 
    constructor (
        private fb: FormBuilder,
        private http: HttpClient,
        private router: Router,
        private authService: AuthService
    ) {}
 
    ngOnInit() {
        this.loginForm = this.fb.group({
            username: new FormControl('', Validators.required),
            password: new FormControl('', Validators.required)
        });
    }
 
    public onFormSubmit() {
        if (this.loginForm.valid) {
            const body = {
                username: this.loginForm.get("username")?.value,
                password: this.loginForm.get("password")?.value
            };
            this.http.post<boolean>("http://localhost:9000/authenticate", body, {observe: 'response'}). subscribe((res) => {
                if (res) {
                    this.authService.username = this.loginForm.get("username")?.value.trim();
                    this.authService.password = this.loginForm.get("password")?.value;

                    // const token = btoa(this.loginForm.get("username")?.value + ':' + this.loginForm.get("password")?.value);
                    // const userData = {
                    //     userName: this.loginForm.get("username")?.value,
                    //     authData: token
                    // };
        
                    // sessionStorage.setItem('medilaboData', JSON.stringify(userData));

                    this.router.navigate(
                        ['/patients']
                    );
                } else {
                    this.loginError = true;
                    this.loginErrorMessage = "Unauthorized access."
                }
            });
        }
    }
} 