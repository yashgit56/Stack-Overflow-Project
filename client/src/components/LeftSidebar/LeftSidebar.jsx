import React from "react";
import "./LeftSidebar.css";
import { NavLink } from "react-router-dom";
import Globe from "../../assets/Globe.svg";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTag } from "@fortawesome/free-solid-svg-icons";
import { faQuestion } from '@fortawesome/free-solid-svg-icons';
import {
  faHome,
  faGlobe,
  faTags,
  faUser,
} from "@fortawesome/free-solid-svg-icons";

const LeftSidebar = ({ slideIn, handleSlideIn }) => {
  const slideInStyle = {
    transform: "translateX(0%)",
  };

  const slideOutStyle = {
    transform: "translateX(-100%)",
  };

  return (
    <div
      className="left-sidebar"
      style={slideIn ? slideInStyle : slideOutStyle}
    >
      <nav className="side-nav">
        <button onClick={() => handleSlideIn()} className="nav-btn">
          <NavLink to="/" className="side-nav-links" activeClassName="active">
            <FontAwesomeIcon icon={faHome} />
            <p style={{ paddingLeft: "10px" }}>Home</p>
          </NavLink>
        </button>
        <div className="side-nav-div">
          <div>
            <p>PUBLIC</p>
          </div>
          <button onClick={() => handleSlideIn()} className="nav-btn">
            <NavLink
              to="/Questions"
              className="side-nav-links"
              activeclassname="active"
            >
              <FontAwesomeIcon icon={faQuestion} />
              <p style={{ paddingLeft: "10px" }}> Questions </p>
            </NavLink>
          </button>
          <button onClick={() => handleSlideIn()} className="nav-btn">
            <NavLink
              to="/Tags"
              className="side-nav-links"
              activeClassName="active"
              style={{ paddingLeft: "40px" }}
            >
              <FontAwesomeIcon icon={faTag} />
              <p style={{ paddingLeft: "10px" }}>Tags</p>
            </NavLink>
          </button>
          <button onClick={() => handleSlideIn()} className="nav-btn">
            <NavLink
              to="/Users"
              className="side-nav-links"
              activeClassName="active"
              style={{ paddingLeft: "40px" }}
            >
              <FontAwesomeIcon icon={faUser} />
              <p style={{ paddingLeft: "10px" }}>Users</p>
            </NavLink>
          </button>
        </div>
      </nav>
    </div>
  );
};

export default LeftSidebar;
