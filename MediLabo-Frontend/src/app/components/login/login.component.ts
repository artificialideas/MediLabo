import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { environment } from 'src/environment/environment';
 
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
            this.authService.getAccess({ 
                username: this.loginForm.get("username")?.value,
                password: this.loginForm.get("password")?.value
            }).subscribe(
                (response: any) => {
                    if (response) {
                        const token = this.authService.createCredentials(this.loginForm.get("username")?.value, this.loginForm.get("password")?.value);
                        const userData = {
                            userName: this.loginForm.get("username")?.value,
                            authData: token
                        };
            
                        sessionStorage.setItem('userData', JSON.stringify(userData));
                        //this.http.defaults.headers.common['Authorization'] = 'Basic ' + token;
                        this.router.navigate(['/']);
                    } else {
                        this.loginError = true;
                        this.loginErrorMessage = "Unauthorized access."
                    }
                },
                (error: string) => {
                    this.loginError = true;
                    this.loginErrorMessage = 'Error during login:' + error;
                }
            );
        }
    }
} 