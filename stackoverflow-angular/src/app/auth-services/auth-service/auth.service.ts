import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map, tap } from 'rxjs';
import { StorageService } from '../storage-service/storage.service';

const BASIC_URL = ['http://localhost:8091/'] ;
export const AUTH_HEADER = "authorization" ;

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private storage: StorageService) { 
  
  }

  login(loginRequest: any): Observable<any> {
    return this.http.post(BASIC_URL + "authentication", loginRequest,
    {observe: "response"}).pipe(tap(__ => this.log("User Authentication")),
     map((res : HttpResponse<any>) => {
      let temp : any = {"userId":1};
      this.storage.saveUser(temp);
      let bearedToken : string = "fenilyashjay";
      this.storage.saveToken(bearedToken);
      return res;
     }))
  }

  log(message: string): void{
    console.log("User Auth Service" + message) ;
  }
  
  register(registerRequest: any): Observable<any> {
    return this.http.post(BASIC_URL + "register" , registerRequest ) ;
  }
}
