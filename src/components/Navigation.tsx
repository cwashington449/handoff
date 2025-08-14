import { Button } from '@/components/ui/button';
import { ArrowRight, Menu, X } from 'lucide-react';
import { useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import { analytics } from '@/lib/analytics';

export function Navigation() {
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const location = useLocation();

  const scrollToSection = (sectionId: string) => {
    if (location.pathname === '/') {
      // If on home page, scroll to section
      const element = document.getElementById(sectionId);
      if (element) {
        element.scrollIntoView({ behavior: 'smooth' });
        analytics.buttonClick(`nav_${sectionId}`, 'navigation');
      }
    }
    setIsMenuOpen(false);
  };

  const handleNavClick = (page: string) => {
    analytics.buttonClick(`nav_${page}`, 'navigation');
    setIsMenuOpen(false);
  };

  return (
    <nav className="fixed top-0 left-0 right-0 z-50 bg-white/95 backdrop-blur-sm border-b border-gray-100">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between items-center h-16">
        <div className="flex items-center">
          <div className="flex-shrink-0">
            <Link 
              to="/" 
              className="text-2xl font-bold text-gray-900 hover:text-blue-600 transition-colors"
              onClick={() => handleNavClick('home')}
            >
              handoff
            </Link>
          </div>
        </div>
          
          <div className="hidden md:block">
            <div className="ml-10 flex items-baseline space-x-8">
              {location.pathname === '/' ? (
                <button 
                  onClick={() => scrollToSection('how-it-works')} 
                  className="text-gray-600 hover:text-gray-900 px-3 py-2 text-sm font-medium transition-colors"
                >
                  How it Works
                </button>
              ) : (
                <Link 
                  to="/"
                  className="text-gray-600 hover:text-gray-900 px-3 py-2 text-sm font-medium transition-colors"
                  onClick={() => handleNavClick('how_it_works')}
                >
                  How it Works
                </Link>
              )}
              <Link 
                to="/creators"
                className={`px-3 py-2 text-sm font-medium transition-colors ${
                  location.pathname === '/creators' 
                    ? 'text-blue-600 font-semibold' 
                    : 'text-gray-600 hover:text-gray-900'
                }`}
                onClick={() => handleNavClick('creators')}
              >
                For Creators
              </Link>
              <Link 
                to="/finishers"
                className={`px-3 py-2 text-sm font-medium transition-colors ${
                  location.pathname === '/finishers' 
                    ? 'text-orange-600 font-semibold' 
                    : 'text-gray-600 hover:text-gray-900'
                }`}
                onClick={() => handleNavClick('finishers')}
              >
                For Finishers
              </Link>
              <Link 
                to="/pricing"
                className={`px-3 py-2 text-sm font-medium transition-colors ${
                  location.pathname === '/pricing' 
                    ? 'text-green-600 font-semibold' 
                    : 'text-gray-600 hover:text-gray-900'
                }`}
                onClick={() => handleNavClick('pricing')}
              >
                Pricing
              </Link>
            </div>
          </div>

          <div className="hidden md:flex items-center space-x-4">
            <Button 
              variant="ghost" 
              size="sm"
              onClick={() => analytics.buttonClick('sign_in', 'navigation')}
            >
              Sign In
            </Button>
            <Link to="/creators">
              <Button 
                size="sm" 
                className="bg-blue-600 hover:bg-blue-700"
                onClick={() => analytics.buttonClick('get_started_nav', 'navigation')}
              >
                Get Started
                <ArrowRight className="ml-2 h-4 w-4" />
              </Button>
            </Link>
          </div>

          <div className="md:hidden">
            <Button
              variant="ghost"
              size="sm"
              onClick={() => setIsMenuOpen(!isMenuOpen)}
            >
              {isMenuOpen ? <X className="h-6 w-6" /> : <Menu className="h-6 w-6" />}
            </Button>
          </div>
        </div>
      </div>

      {isMenuOpen && (
        <div className="md:hidden">
          <div className="px-2 pt-2 pb-3 space-y-1 sm:px-3 bg-white border-b border-gray-100">
            {location.pathname === '/' ? (
              <button 
                onClick={() => scrollToSection('how-it-works')} 
                className="text-gray-600 hover:text-gray-900 block px-3 py-2 text-base font-medium w-full text-left"
              >
                How it Works
              </button>
            ) : (
              <Link 
                to="/"
                className="text-gray-600 hover:text-gray-900 block px-3 py-2 text-base font-medium"
                onClick={() => handleNavClick('how_it_works')}
              >
                How it Works
              </Link>
            )}
            <Link 
              to="/creators"
              className="text-gray-600 hover:text-gray-900 block px-3 py-2 text-base font-medium"
              onClick={() => handleNavClick('creators')}
            >
              For Creators
            </Link>
            <Link 
              to="/finishers"
              className="text-gray-600 hover:text-gray-900 block px-3 py-2 text-base font-medium"
              onClick={() => handleNavClick('finishers')}
            >
              For Finishers
            </Link>
            <Link 
              to="/pricing"
              className="text-gray-600 hover:text-gray-900 block px-3 py-2 text-base font-medium"
              onClick={() => handleNavClick('pricing')}
            >
              Pricing
            </Link>
            <div className="px-3 py-2 space-y-2">
              <Button 
                variant="ghost" 
                size="sm" 
                className="w-full"
                onClick={() => analytics.buttonClick('sign_in_mobile', 'navigation')}
              >
                Sign In
              </Button>
              <Link to="/creators" className="block">
                <Button 
                  size="sm" 
                  className="w-full bg-blue-600 hover:bg-blue-700"
                  onClick={() => analytics.buttonClick('get_started_mobile', 'navigation')}
                >
                  Get Started
                  <ArrowRight className="ml-2 h-4 w-4" />
                </Button>
              </Link>
            </div>
          </div>
        </div>
      )}
    </nav>
  );
}
