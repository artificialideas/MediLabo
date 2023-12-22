import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
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
        private router: Router
    ) {}
 
    ngOnInit() {
        this.loginForm = this.fb.group({
            username: new FormControl('', Validators.required),
            password: new FormControl('', Validators.required)
        });
    }
 
    public onFormSubmit() {
        if (this.loginForm.valid) {
            if ((this.loginForm.get("username")?.value === environment.auth.user) && 
                    (this.loginForm.get("password")?.value) === environment.auth.pwd) {
                this.router.navigate(
                    ['/']
                );
            } else {
                this.loginError = true;
                this.loginErrorMessage = "Unauthorized access."
            }
        }
    }
} 