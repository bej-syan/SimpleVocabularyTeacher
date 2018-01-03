package controllers

import javax.inject.Inject

import play.api.i18n.Lang
import play.api.libs.streams.ActorFlow
import play.api.mvc.{AbstractController, ControllerComponents, WebSocket}
import actors.QuizActor
import akka.actor.ActorSystem
import akka.stream.Materializer
import services.VocabularyService

class Quiz @Inject()(implicit system: ActorSystem,
                     mat: Materializer, cc: ControllerComponents,
                     vocabularyService: VocabularyService,
                    ) extends AbstractController(cc) {

  def quiz(sourceLanguage: Lang,
           targetLanguage: Lang) = Action {
    val res = vocabularyService.findRandomVocabulary(sourceLanguage, targetLanguage)

    res.map { v =>
      Ok(v.word)
    } getOrElse {
      NotFound
    }
  }

  def check(sourceLanguage: Lang,
            word: String,
            targetLanguage: Lang,
            translation: String) = Action {
    Ok
  }

  def quizEndpoint(sourceLang: Lang, targetLang: Lang) =
    WebSocket.accept[String, String] { implicit request =>
      ActorFlow.actorRef { out =>
        QuizActor.props(out, sourceLang, targetLang, vocabularyService)
      }
    }
}
