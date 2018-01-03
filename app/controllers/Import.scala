package controllers

import javax.inject.Inject
import play.api.mvc._

import models.Vocabulary
import services.VocabularyService

class Import @Inject()(cc: ControllerComponents,
                       vocabulary: VocabularyService) extends AbstractController(cc) {

  import play.api.i18n.Lang

  def importWord(sourceLanguage: Lang,
                 word: String,
                 targetLanguage: Lang,
                 translation: String) =
    Action {
      val added = vocabulary.addVocabulary(
        Vocabulary(sourceLanguage, targetLanguage, word, translation)
      )

      if (added) {
        Ok
      } else {
        Conflict
      }

    }
}