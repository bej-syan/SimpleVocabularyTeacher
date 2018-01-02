package controllers

import javax.inject.Inject

import play.api.i18n.Lang
import play.api.mvc.{AbstractController, ControllerComponents}
import services.VocabularyService

class Quiz @Inject()(cc: ControllerComponents,
                     vocabularyService: VocabularyService) extends AbstractController(cc) {

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
}