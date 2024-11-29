
import React from 'react'
import styles from './Button.module.css'

function Button () {

  const inLineStyles = {
    backgroundColor: "hsl(250, 100%, 50%)",
    color: "white",
    padding: "10px 20px",
    borderRadius: "5px",
    border: "none",
    cursor: "pointer",
  }  


  return (
    <>
        <button className={styles.button}> Click me! </button>
        <button style={inLineStyles}> InLine </button>
    </>
  )
}

export default Button
