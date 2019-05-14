package lingaraj.hourglass.in.base;

import timber.log.Timber;

/**
 * class ensure no Log has been caputered in release build
 */
public class TimberReleaseTree extends Timber.Tree {

  @Override
  protected void log(int priority, String tag, String message, Throwable t) {

  }

}
