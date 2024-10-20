/*
 * Copyright 2010 Aalto University, ComNet
 * Released under GPLv3. See LICENSE.txt for details.
 */
package core;

/**
 * Missing settings related error
 *
 * @author David Kelly
 */
public class MissingSettingsError extends SimError {

  public MissingSettingsError(String cause) {
    super(cause);
  }

  public MissingSettingsError(String cause, Exception e) {
    super(cause,e);
  }

  public MissingSettingsError(Exception e) {
    super(e);
  }

}
