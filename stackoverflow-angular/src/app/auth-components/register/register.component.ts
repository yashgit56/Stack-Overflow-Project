import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth-services/auth-service/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  signupForm: FormGroup = new FormGroup({});
  constructor(
    private service: AuthService,
    private fb : FormBuilder,
    private snackBar : MatSnackBar,
    private router: Router,
  ){}

  ngOnInit(){
    this.signupForm = this.fb.group({
      username:['',Validators.required],
      email:['',Validators.required],
      password:['',Validators.required],
      confirmPassword:['',Validators.required ],
    },{ validator: this.confirmationValidator})
  }

  private confirmationValidator(fg: FormGroup){
    const password = fg.get("password")?.value;
    const confirmPassword = fg.get("confirmPassword")?.value;
    if(password != confirmPassword){
      fg.get("confirmPassword")?.setErrors({ passwordMismatch: true});
    }else{
      fg.get("confirmPassword")?.setErrors(null);
    }

  }

  signup(event: Event) {
    event.preventDefault();
    console.log("Hello world");
    console.log(this.signupForm.value);
    this.service.register(this.signupForm.value).subscribe((response)=>{
      console.log(response);
      if(response.id != null){
        this.snackBar.open("You're registred successfully","Close",{duration:5000});
        this.router.navigateByUrl("/login");
      }else{
        this.snackBar.open(response.message, "Close", {duration:5000});
      }
    }, (error:any)=>{
      this.snackBar.open("Registration failed, Please try again later","Close",{duration:5000});
    })
    // Additional signup logic her
  }

}
