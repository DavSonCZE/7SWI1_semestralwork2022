import type { NextPage } from 'next'
import Head from 'next/head'
import Image from 'next/image'
import styles from '../styles/Home.module.css'
import useUser from "../lib/useUser";
import {Toaster} from "react-hot-toast";

const Home: NextPage = () => {

  return (
      <>
      <Head>
        <title>Review App</title>
        <meta name="description" content="Game Review App"/>
        <meta name="author" content="David Slonka"/>
        <meta name="keywords" content="GameReviewApp, ReviewApp"/>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="icon" href="/favicon.ico" />
      </Head>
            <Image src='/intor_img.png' alt='intro_img' width={252} height={609}/>
          <div id="main_content">
            <p>Web, Přijď zrecenzovat hru co jsi si zahrál. <br/><br/>

              1) Najdi si SVOJI HRU!<br/>
              2) Přidej recenzi.<br/>
              3) Napiš comment<br/>
              4) A dej skóre.<br/>
            </p>
          </div>
      </>
  )
}

export default Home
