import { Component } from '@angular/core';

@Component({
  selector: 'app-about-our-team',
  templateUrl: './about-our-team.component.html',
  styleUrls: ['./about-our-team.component.scss']
})
export class AboutOurTeamComponent {
  imagePath:string[] = [
    'assets/images/yash.jpeg',
    'assets/images/fenil.jpeg',
    'assets/images/jay.jpeg'
  ];

  getImagePath(index: number): string {
    if (index >= 0 && index < this.imagePath.length) {
        return this.imagePath[index];
    }
    return 'assets/images/yash.jpeg';
  }
}
